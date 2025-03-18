const http = require('http');

const port = 3000;

const rotas = {
    "/": "Curso de Node.Js"
};

const server = http.createServer((req, res) => {
    res.writeHead(200, { 'Content-Type': 'text/plain' });
    res.end(rotas[req.url]);
});

server.listen(3000, () => { 
    console.log('Server rodando no http://localhost:3000');
});