import axios from "axios";
const LOGIN_URL = "http://localhost:8080/login"

class AuthenticationRequest {

    async sendLoginRequest() {
        return await axios.get(LOGIN_URL,
            {
                headers: {
                    crossorigin:true
                }
               
            }
        );
    }

    async processLoginForm(username, password) {
       
        var res = await this.sendLoginRequest();
        console.log(res);
        var token = this.getCSRFtoken(res.data);
    
        await this.loginWithCredentials(username, password, token);

    }

    getCSRFtoken(data) {
        var csrfIdx = data.indexOf("_csrf");
        var sliced = data.slice(csrfIdx);
        var startIdx = sliced.indexOf("value=") + 7;
        var endIdx = sliced.indexOf("\" />");
        var token = sliced.slice(startIdx, endIdx);
        console.log(token);
        return token;
    }

    // getJSESSIONIDCookie() {
    //     const cookie = this.$cookies.get("JSESSIONID");
    //     console.log("jsessionid cookie: " + cookie);
    //     return cookie;
    // }

    async loginWithCredentials(username, password, csrfToken) {

        console.log(`login with username: ${username} 
        \npassword: ${password}
        \ncsrf token: ${csrfToken}`);

        await axios({
            method: "post",
            url: LOGIN_URL,
            data: "username="+username 
            + "&password=" + password
            // +"&_csrf=" + csrfToken,
          })
          .then(function (response) {
            //handle success
            console.log(response);
          })
          .catch(function (response) {
            //handle error
            console.log(response);
          });
    }

}

export default new AuthenticationRequest();