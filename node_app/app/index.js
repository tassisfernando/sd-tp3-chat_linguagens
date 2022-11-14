const net = require('net');
const prompt = require('prompt-sync')({sigint: true});

const HOST = '127.0.0.1'; // IP do nosso host (local)
const PORT = 6969; // definindo a porta

// Cria a instância do Server e aguarda uma conexão
net.createServer(sock => {
    sock.setEncoding('utf8');

    // Ao receber uma nova conexão um objeto socket é associado à conexão automaticamente
    console.log(`USUÁRIO CONECTADO: ${sock.remoteAddress}:${sock.remotePort}`);

    // Evento que recebe mensagens de usuários
    sock.on('data', data => {
        // dados foram recebidos no socket
        console.log(`> ${data}`);
      
        // faz a leitura de mensagem via terminal
        const message = prompt('> Envie a sua mensagem: ');
        
        // envia a mensagem de resposta
        const buffer = Buffer.from(message);
        sock.setNoDelay(true);
        sock.write(buffer.toString()+'\r\n');
    });

    // Evento close, que fecha a conexão
    sock.on('close', () => {
        // conexão fechada
        console.log(`CLOSED: ${sock.remoteAddress}:${sock.remotePort}`);
    });

    // Evento end, que finaliza o servidor
    sock.on('end', () => {
        console.log('Servidor finalizado');
    });

}).listen(PORT, HOST);

console.log('Servidor iniciado ' + HOST +':'+ PORT);