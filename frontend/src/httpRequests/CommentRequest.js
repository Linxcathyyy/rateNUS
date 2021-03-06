import axios from "axios";

const COMMENT_API_BASE_URL = "https://www.ratenus.cyou:8080/comment";
const COMMENT_USER_API_BASE_URL = "https://www.ratenus.cyou:8080/comment/user";

class CommentRequest {
  // Get a partial list of comments based on userId
  async getCommentsByUserId(userId, jwtToken) {
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };
    return await axios.post(COMMENT_USER_API_BASE_URL +"/" + userId, {
      orderBy: "id",
      isLowToHigh: true,
    }, {
      headers: headers,
    });
  }

  async editComment(commentId, comment, rating, jwtToken, username) {
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };
    const url = `${COMMENT_API_BASE_URL}/${commentId}?token=${jwtToken}&username=${username}`
    return await axios.put(url, {
      text: comment, 
      rating: rating
    }, {
      headers: headers,
    });
  }

  async deleteComment(commentId, jwtToken, username) {
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };
    const url = `${COMMENT_API_BASE_URL}/${commentId}?token=${jwtToken}&username=${username}`
    return await axios.delete(url,{
      headers: headers,
    });
  }

  // Below is for admin
  async getAllComments(jwtToken) {
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };
    return axios.get(COMMENT_API_BASE_URL,{
      headers: headers,
    });
  }
}

export default new CommentRequest();
