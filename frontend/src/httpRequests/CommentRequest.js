import axios from "axios";

const COMMENT_API_BASE_URL = "http://localhost:8080/comment";
const COMMENT_USER_API_BASE_URL = "http://localhost:8080/comment/user";

class CommentRequest {
  // Get a partial list of comments based on userId
  async getCommentsByUserId(userId) {
    return await axios.post(COMMENT_USER_API_BASE_URL +"/" + userId, {
      orderBy: "id",
      isLowToHigh: true,
    });
  }

  async editComment(commentId, commentObj,) {
    return await axios.put(COMMENT_API_BASE_URL + "/" + commentId, commentObj);
  }

  async deleteComment(commentId) {
    return await axios.delete(COMMENT_API_BASE_URL + "/" + commentId);
  }
}

export default new CommentRequest();
