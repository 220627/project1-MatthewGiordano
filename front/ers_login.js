const url = "http://localhost:5500"

document.getElementById("loginButton").addEventListener('click', loginFunc)

async function loginFunc(){

    console.log("hello");

    //receiving user inputs for logging in
    let user_username = document.getElementById("username").value;
    let user_password = document.getElementById("password").value;

    //sending login cred as json
    let user = {
        username:user_username,
        password:user_password,
    }

    console.log(user);

    let response = await fetch(url + "/login", {

        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"

    })

    console.log(response.status);

   if(response.status === 200){

    document.getElementById("login-row").innerText="Welcome!";

    if(user_role_id == 2){
    location.replace("http://127.0.0.1:5500/ers.html")
    } else {
    location.replace("http://127.0.0.1:5500/ers_manager.html")
    }

} else {
        document.getElementById("login-row").innerText="Login Failed";
        }

}