import axios from "axios";
const LOGIN_URL = "http://localhost:8080/comment"

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

    async processLoginForm() {
        var res = await this.sendLoginRequest();
        console.log(res);
    }

}

export default new AuthenticationRequest();