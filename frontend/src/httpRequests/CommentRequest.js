import axios from "axios";

const COMMENT_API_BASE_URL = "http://localhost:8080/comment";
const COMMENT_USER_API_BASE_URL = "http://localhost:8080/comment/user";

class CommentRequest {
  // Get a partial list of comments based on userId
  async getCommentsByUserId(userId, jwtToken) {
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };
    return await axios.post(COMMENT_USER_API_BASE_URL +"/" + userId, {
      orderBy: "id",
      isLowToHigh: true,
    }, { headers: headers });
  }

  async editComment(commentId, comment, rating) {
    return await axios.put(COMMENT_API_BASE_URL + "/" + commentId, {
      text: comment, 
      rating: rating
    });
  }

  async deleteComment(commentId) {
    return await axios.delete(COMMENT_API_BASE_URL + "/" + commentId);
  }
}

export default new CommentRequest();
