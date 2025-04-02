const userResolvers = {
  query: {
    users: ({ dataSources }) => dataSources.usersAPI.getUsers(),
    user: ({ dataSources }, { id }) => dataSources.usersAPI.getUser(id),
  },
};

module.exports = userResolvers;
