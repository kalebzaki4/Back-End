const { ApolloServer } = require('apollo-server');
const usersSchema = require('../api/user/schema/user.graphql');

const users = [
    { nome: 'John Doe', ativo: true, email: 'john@example.com' },
    { nome: 'Marcos', ativo: false, email: 'marcos@example.com' }
];

const typeDefs = [usersSchema];

const resolvers = {
    Query: {
        users: () => users
    }
};

const server = new ApolloServer({ typeDefs, resolvers });

server.listen().then(({ url }) => {
    console.log(`🚀 Servidor rodando em ${url}`);
});
