const arrayUsers = [
  { nome: "John Doe", ativo: true, email: "john@example.com" },
  { nome: "Marcos", ativo: false, email: "marcos@example.com" },
];

const userResolvers = {
    query : {
        users: () => arrayUsers,
        primeiroUser: () => arrayUsers[0],
    },
};

module.exports = userResolvers;
