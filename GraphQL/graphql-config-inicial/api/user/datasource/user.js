const { RESTdatasource } = require('apollo-datasource-rest');

class UserAPI extends RESTdatasource {
    constructor() {
        super();
        this.baseURL = 'https://localhost:3000';
    }

    async getUsers() {
        return this.get('/users');
    }

    async getUserById(id) {
        return this.get(`/users/${id}`);
    }
}

module .exports = UserAPI;