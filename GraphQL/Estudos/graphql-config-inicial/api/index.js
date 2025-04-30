const { ApolloServer, gql } = require("apollo-server");

const users = [
  {
    nome: "Lucas",
    ativo: true,
  },
  {
    nome: "João",
    ativo: false,
  },
];

const typeDefs = gql`
  type Users {
    nome: String!
    ativo: Boolean!
    email: String
  }
`;

const server = new ApolloServer({ typeDefs, resolvers });
