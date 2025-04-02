const userResolvers = {
  query: {
    users: ({ dataSources }) => dataSources.usersAPI.getUsers(),
  },
};

module.exports = userResolvers;
