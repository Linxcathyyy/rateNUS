import axios from "axios";

const USER_ID_BASE_URL = "https://www.ratenus.cyou:8080/user/names";

class UserUtil {
  getInitials(name) {
    let initials = name.split(" ");

    if (initials.length > 1) {
      initials = initials.shift().charAt(0) + initials.pop().charAt(0);
    } else {
      initials = name.substring(0, 2);
    }

    return initials.toUpperCase();
  }
  async getUsernamesByUserIds(userIds) {
    return await axios.post(USER_ID_BASE_URL, userIds);
  }
}

export default new UserUtil();
