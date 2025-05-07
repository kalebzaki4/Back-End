const fs = require("fs");
const path = require("path");

const CaminhoTexto = path.join(__dirname, "../arquivos/texto-aprendizado.txt");

fs.readFile(CaminhoTexto, "utf8", (err, data) => {
  if (err) {
    console.error("Error reading the file:", err);
    return;
  }
  verificaPalavrasDuplicadas(data);
});

function verificaPalavrasDuplicadas(data) {
  const paragrafos = data.split(/\n\n/);
  paragrafos.forEach((paragrafo, index) => {
    console.log(`Parágrafo ${index + 1}:`);
    const palavras = paragrafo.split(/\s+/);
    const palavrasDuplicadas = new Set();
    const palavrasUnicas = new Set();

    for (const palavra of palavras) {
      const palavraNormalizada = palavra.toLowerCase();
      if (palavraNormalizada.length < 3) continue;
      if (palavrasUnicas.has(palavraNormalizada)) {
        palavrasDuplicadas.add(palavraNormalizada);
      } else {
        palavrasUnicas.add(palavraNormalizada);
      }
    }

    if (palavrasDuplicadas.size > 0) {
      console.log(
        "Palavras duplicadas encontradas:",
        Array.from(palavrasDuplicadas)
      );
    } else {
      console.log("Nenhuma palavra duplicada encontrada.");
    }
  });
}
