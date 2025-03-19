const http = require('http');

const PORT = 3000;
const HOSTNAME = 'localhost';

const routes = {
    "/": "Curso de Node.Js",
    "/sobre": "Sobre o curso de Node.Js",
    "/contato": "Contato do curso de Node.Js"
};

const server = http.createServer((req, res) => {
    const responseText = routes[req.url];
    
    if (responseText) {
        res.writeHead(200, { 'Content-Type': 'text/plain' });
        res.end(responseText);
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('404 - Página não encontrada');
    }
});

server.listen(PORT, HOSTNAME, () => { 
    console.log(`Server rodando em http://${HOSTNAME}:${PORT}`);
});