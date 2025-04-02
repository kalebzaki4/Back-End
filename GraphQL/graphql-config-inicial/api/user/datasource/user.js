const { RESTdatasource } = require('apollo-datasource-rest');

class UserAPI extends RESTdatasource {
    constructor() {
        super();
        this.baseURL = 'https://localhost:3000';
    }

    async getUsers() {
        return this.get('/users');
    }
}

module .exports = UserAPI;