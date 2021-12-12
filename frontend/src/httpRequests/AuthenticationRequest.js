import axios from "axios";
const LOGIN_URL = "http://localhost:8080/login"

class AuthenticationRequest {

    async sendLoginRequest() {
        return await axios.get(LOGIN_URL);
    }

    async processLoginForm() {
        var res = await this.sendLoginRequest();
        console.log(res);
    }

}

export default new AuthenticationRequest();