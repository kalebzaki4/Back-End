const { ApolloServer } = require("apollo-server");
const usersSchema = require("../api/user/schema/user.graphql");
const usersResolvers = require("../api/user/resolvers/userResolvers");

const typeDefs = [usersSchema];
const resolvers = [usersResolvers];

const server = new ApolloServer({ typeDefs, resolvers });

server.listen().then(({ url }) => {
  console.log(`🚀 Servidor rodando em ${url}`);
});
