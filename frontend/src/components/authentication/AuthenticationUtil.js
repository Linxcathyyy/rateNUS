
class AuthenticationUtil {
    parseJWTToken(token) {
        return token.replace("Bearer ", "");
    }
}

export default new AuthenticationUtil();