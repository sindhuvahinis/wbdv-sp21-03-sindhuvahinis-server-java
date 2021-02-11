// fields
var usernameFld
var passwordFld
var firsNameFld
var lastNameFld
var roleFld
var defaultRoleVal

var theTableBody

//buttons
var $createBtn
var $updateBtn
var addUserBtn

var userService = new UserService();

var users = [];

function createUser(user) {
    userService.createUser(user).then(function (actualUser) {
        users.push(actualUser)
        renderUsers(users)
    })
}

var selectedUser = null

function selectUser(event) {
    console.log(event.target)
    var selectBtn = jQuery(event.target)
    var theId = selectBtn.attr("id")
    selectedUser = users.find(user => user._id === theId)
    console.log(selectUser.username)
    usernameFld.val(selectedUser.username)
    passwordFld.val(selectedUser.password)
    firsNameFld.val(selectedUser.first_name)
    lastNameFld.val(selectedUser.last_name)
    roleFld.val(selectedUser.role)
}

function updateUser() {
    selectedUser.username = usernameFld.val()
    selectedUser.password = passwordFld.val()
    selectedUser.first_name = firsNameFld.val()
    selectedUser.last_name = lastNameFld.val()
    selectedUser.role = roleFld.val()

    userService.updateUser(selectedUser._id, selectedUser).then(status => {
        console.log(status)
        var index = users.findIndex(user => user._id === selectedUser._id)
        users[index] = selectedUser
        renderUsers(users)
    })

    // clearing the fields
    usernameFld.val("")
    passwordFld.val("")
    firsNameFld.val("")
    lastNameFld.val("")
    roleFld.val(defaultRoleVal)
}

function deleteUser(event) {
    var deleteBtn = jQuery(event.target)
    var theIndex = deleteBtn.attr("id")
    var theId = users[theIndex]._id

    userService.deleteUser(theId).then(function (status) {
        console.log(status)
        users.splice(theIndex, 1)
        renderUsers(users)
    })
}

function renderUsers(users) {
    theTableBody.empty()
    for (var i = 0; i < users.length; i++) {
        var user = users[i]
        theTableBody.prepend(`
            <tr>
                <td>${user.username}</td>
                <td class="ss-hide-password">${user.password}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
                <td>${user.role}</td>
                <td>
                    <span class="pull-right">
                        <button class="ss-btn ss-delete-btn" title="delete" id=${i}>
                            <i class="fa-lg fa fa-trash ss-icon ss-row-icon ss-delete-icon"></i>
                        </button>
                        <button class="ss-btn ss-select-btn" title="select" id=${user._id}>
                            <i class="fa-lg fa fa-pencil-alt ss-icon ss-row-icon"></i>
                        </button>
                    </span>
                </td>
            </tr>`
        )
    }

    jQuery(".ss-delete-btn")
        .click(deleteUser)
    jQuery(".ss-select-btn")
        .click(selectUser)
}

function main() {

    theTableBody = jQuery("tbody")

    usernameFld = $(".ss-username-fld")
    passwordFld = $(".ss-password-fld")
    firsNameFld = $(".ss-firstname-fld")
    lastNameFld = $(".ss-lastname-fld")
    roleFld = $(".ss-role-fld")
    defaultRoleVal = "FACULTY"

    $createBtn = $(".ss-create-btn")
    $updateBtn = $(".ss-update-btn")

    $updateBtn.click(updateUser)

    $createBtn.click(() => {
            createUser({
                    username: usernameFld.val(),
                    password: passwordFld.val(),
                    first_name: firsNameFld.val(),
                    last_name: lastNameFld.val(),
                    role: roleFld.val()
                }
            )

            // clearing the fields
            usernameFld.val("")
            passwordFld.val("")
            firsNameFld.val("")
            lastNameFld.val("")
            roleFld.val(defaultRoleVal)
        }
    )

    userService.findAllUsers().then(function (usersFromServer) {
        users = usersFromServer
        renderUsers(users)
    })
}

jQuery(main)