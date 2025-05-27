const fs = require("fs");
const path = require("path");

const CaminhoTexto = path.join(__dirname, "../arquivos/texto-aprendizado.txt");

fs.readFile(CaminhoTexto, "utf8", (err, data) => {
  if (err) {
    console.error("Error reading the file:", err);
    return;
  }
  const textoLimpo = removeCaracteresEspeciais(data);
  const textoFormatado = removeParagrafosVazios(textoLimpo);
  const paragrafos = separaEmParagrafos(textoFormatado);
  exibeTextoComParagrafos(paragrafos);
  verificaPalavrasDuplicadas(paragrafos);
});

function removeCaracteresEspeciais(data) {
  return data.replace(/[^\w\s]/gi, "");
}

function removeParagrafosVazios(data) {
  return data
    .split("\n")
    .map((linha) => linha.trim())
    .filter((linha) => linha.length > 0)
    .join("\n");
}

function separaEmParagrafos(data) {
  return data.split(/\n\n/);
}

function exibeTextoComParagrafos(paragrafos) {
  console.log("Texto completo com parágrafos:");
  paragrafos.forEach((paragrafo, index) => {
    console.log(`\nParágrafo ${index + 1}:\n${paragrafo}`);
  });
}

function verificaPalavrasDuplicadas(paragrafos) {
  paragrafos.forEach((paragrafo, index) => {
    console.log(`\nParágrafo ${index + 1}:\n${paragrafo}`);
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
