const { ApolloServer } = require("apollo-server");
const { gql } = require("graphql-tag");

const animes = [
  { id: 1, title: "Naruto", genre: "Action" },
  { id: 2, title: "One Piece", genre: "Adventure" },
  { id: 3, title: "Attack on Titan", genre: "Action" },
  { id: 4, title: "Death Note", genre: "Thriller" },
];

const typeDefs = gql`
  type Anime {
    id: ID!
    title: String!
    genre: String!
  }

  type Query {
    animes: [Anime!]!
  }
`;

const resolvers = {
  Query: {
    animes: () => animes,
  },
};

const server = new ApolloServer({ typeDefs, resolvers });
