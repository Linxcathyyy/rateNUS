import axios from "axios";

const COMMENT_API_BASE_URL = "http://localhost:8080/comment";
const COMMENT_USER_API_BASE_URL = "http://localhost:8080/comment/user";

class CommentRequest {
  // Get a partial list of comments based on userId
  async getCommentsByUserId(userId, pageNum, pageSize) {
    return await axios.post(COMMENT_USER_API_BASE_URL +"/" + userId, {
      orderBy: "id",
      isLowToHigh: true,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  async editComment(commentId) {
    return axios.put(COMMENT_API_BASE_URL + "/" + commentId);
  }

  async deleteComment(commentId) {
    return axios.delete(COMMENT_API_BASE_URL + "/" + commentId);
  }
}

export default new CommentRequest();
