import axios from "axios";
const LOGIN_URL = "http://3.142.45.139:8080/login"
const SIGNUP_URL = "http://3.142.45.139:8080/signup"
const CONFIRM_URL = "http://3.142.45.139:8080/registrationConfirm"
const BASE_URL = "http://3.142.45.139:8080/"
class AuthenticationRequest {
    async loginWithCredentials(username, password) {
        var response = await axios.post(LOGIN_URL, {
            "username": username,
            "password": password
        })
            .then(function (response) {
                //handle success
                return response;
            })
        return response;
    }

    async signUpWithCredentials(username, password, email) {
        var response = await axios.post(SIGNUP_URL, {
            "username": username,
            "password": password,
            "email": email
        })
            .then(function (response) {
                //handle success
                return response;
            })
        return response;
    }

    async sendConfirmationRequest(token) {
        var res = await axios.get(CONFIRM_URL + "?token=" + token);
        return res;
    }

    async sendForgetPasswordRequest(email) {
        var res = await axios.post(BASE_URL + "forgetPassword", {
            "email": email
        });
        return res;
    }

    async sendResetPasswordRequest(newPassword, token) {
        var res = await axios.post(BASE_URL + "resetPassword" + "?token=" + token, {
            "password": newPassword
        })
        return res;
    }

    async checkLogin(jwtToken) {
        const headers = {
            Authorization: "Bearer " + jwtToken,
        };
        var res = await axios.get(BASE_URL + "checkLogin", {
            headers: headers
        });
        return res;
    }

}

export default new AuthenticationRequest();