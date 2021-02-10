function UserService() {

    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.createUser = createUser;
    this.updateUser = updateUser;
    this.deleteUser = deleteUser;
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/sindhuvahinis/users';

    var self = this;

    function createUser(user) {
        return fetch(self.url, {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify(user)
            }
        ).then(response => response.json())
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json()
            });
    }

    function findUserById(courseId) {

    }

    function updateUser(userId, user) {
        return fetch(`${self.url}/${userId}`, {
            method: 'PUT',
            headers: {'content-type': 'application-json'},
            body: JSON.stringify(user)
        }).then(response => response.json())

    }

    function deleteUser(userId) {
        return fetch(`${self.url}/${userId}`, {
            method: 'DELETE'
        })
    }
}