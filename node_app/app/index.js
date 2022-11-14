const net = require('net');
const prompt = require('prompt-sync')({sigint: true});

const HOST = '127.0.0.1'; // parametrizar o IP do Listen
const PORT = 6969; // porta TCP LISTEN

// Cria a instância do Server e aguarda uma conexão
net.createServer(sock => {
    sock.setEncoding('utf8');

    // Opa, recebemos uma conexão - um objeto socket é associado à conexão automaticamente
    console.log(`USUÁRIO CONECTADO: ${sock.remoteAddress}:${sock.remotePort}`);

    // Adiciona um 'data' - "event handler" nesta instância do socket
    sock.on('data', data => {
        // dados foram recebidos no socket
        // Escreve a mensagem recebida de volta para o socket (echo)
        console.log(`> ${data}`);
      
        const message = prompt('> Envie a sua mensagem: ');
        
        const buffer = Buffer.from(message);
        sock.setNoDelay(true);
        sock.write(buffer.toString()+'\r\n');
    });

    // Adiciona um 'close' - "event handler" nesta instância do socket
    sock.on('close', () => {
        // conexão fechada
        console.log(`CLOSED: ${sock.remoteAddress}:${sock.remotePort}`);
    });

    sock.on('end', () => {
        console.log('Servidor finalizado');
    });

}).listen(PORT, HOST);

console.log('Servidor iniciado ' + HOST +':'+ PORT);