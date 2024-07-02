use crate::{
  app,
  conf::AppConf,
  utils::{self, load_script},
};
use log::{error, info};
use tauri::{utils::config::WindowUrl, window::WindowBuilder, App, GlobalShortcutManager, Manager};
use wry::application::accelerator::Accelerator;

pub fn init(app: &mut App) -> Result<(), Box<dyn std::error::Error>> {
  info!("stepup");

  let app_conf = AppConf::read();
  let url = app_conf.main_origin.to_string();
  let theme = AppConf::theme_mode();

  app::template::Template::new(utils::app_root().join("scripts"));

  let handle = app.app_handle();
  tauri::async_runtime::spawn(async move {
    app::script::init_script(handle)
      .await
      .unwrap_or_else(|err| {
        error!("script_init_error: {}", err);
      });
  });

  let handle = app.app_handle();
  tauri::async_runtime::spawn(async move {
    info!("stepup_tray");
    app::window::tray_window(&handle);
  });

  if let Some(v) = app_conf.clone().global_shortcut {
    info!("global_shortcut: `{}`", v);
    match v.parse::<Accelerator>() {
      Ok(_) => {
        info!("global_shortcut_register");
        let handle = app.app_handle();
        let mut shortcut = app.global_shortcut_manager();
        shortcut
          .register(&v, move || {
            if let Some(w) = handle.get_window("core") {
              if w.is_visible().unwrap() {
                w.hide().unwrap();
              } else {
                w.show().unwrap();
                w.set_focus().unwrap();
              }
            }
          })
          .unwrap_or_else(|err| {
            error!("global_shortcut_register_error: {}", err);
          });
      }
      Err(err) => {
        error!("global_shortcut_parse_error: {}", err);
      }
    }
  } else {
    info!("global_shortcut_unregister");
  };

  let app_conf2 = app_conf.clone();
  if app_conf.hide_dock_icon {
    #[cfg(target_os = "macos")]
    app.set_activation_policy(tauri::ActivationPolicy::Accessory);
  } else {
    let app = app.handle();
    tauri::async_runtime::spawn(async move {
      let url2 = &url;
      let mut main_win = WindowBuilder::new(&app, "core", WindowUrl::App(url2.into()))
        .title("ChatGPT")
        .resizable(true)
        .fullscreen(false)
        .inner_size(app_conf2.main_width, app_conf2.main_height)
        .theme(Some(theme))
        .always_on_top(app_conf2.stay_on_top)
        .initialization_script(&utils::user_script())
        .initialization_script(&load_script("core.js"))
        .user_agent(&app_conf2.ua_window);

      #[cfg(target_os = "macos")]
      {
        main_win = main_win
          .title_bar_style(app_conf2.clone().titlebar())
          .hidden_title(true);
      }

      if url == "https://chat.openai.com" {
        main_win = main_win
          .initialization_script(include_str!("../vendors/floating-ui-core.js"))
          .initialization_script(include_str!("../vendors/floating-ui-dom.js"))
          .initialization_script(include_str!("../vendors/html2canvas.js"))
          .initialization_script(include_str!("../vendors/jspdf.js"))
          .initialization_script(include_str!("../vendors/turndown.js"))
          .initialization_script(include_str!("../vendors/turndown-plugin-gfm.js"))
          .initialization_script(&load_script("popup.core.js"))
          .initialization_script(&load_script("export.js"))
          .initialization_script(&load_script("markdown.export.js"))
          .initialization_script(&load_script("cmd.js"))
          .initialization_script(&load_script("chat.js"))
      }

      if let Err(err) = main_win.build() {
        error!("core_build_error: {}", err);
      }
    });
  }

  // auto_update
  let auto_update = app_conf.get_auto_update();
  if auto_update != "disable" {
    info!("run_check_update");
    let app = app.handle();
    utils::run_check_update(app, auto_update == "silent", None);
  }

  Ok(())
}
