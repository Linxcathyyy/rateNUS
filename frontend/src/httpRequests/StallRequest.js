import axios from "axios";

const STALL_API_BASE_URL = "http://localhost:8080/stall";
const ADD_COMMENT_API_BASE_URL = "http://localhost:8080/comment";
const GET_COMMENT_API_BASE_URL = "http://localhost:8080/comment/stall";

class StallRequest {
  // Get a partial list of stalls base on startIndex and endIndex
  async getStallList(pageNum, pageSize) {
    return await axios.post(STALL_API_BASE_URL, {
      orderBy: "id",
      isLowToHigh: true,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  getSortedStallList(isHighToLow) {
    return axios.get(STALL_API_BASE_URL + "/isHighToLow/" + isHighToLow);
  }

  // Get individual stall data from server
  getIndividualStall(stallId) {
    return axios.get(STALL_API_BASE_URL + "/" + stallId);
  }

  // Find stalls based on keywords
  findStalls(keyword, pageNum, pageSize) {
    return axios.post(STALL_API_BASE_URL + "/search", {
      keyword: keyword,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  // Post individual stall comment to server
  postStallComment(stallId, comment, rating, jwtToken) {
    // below is temp implementation to see post content
    if (stallId == undefined) {
      // check id is stall id
      throw new Error(
        "Invalid id argument. Id must be a stallId.\nId received: ",
        stallId
      );
    }
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };

    const req = {
      targetId: stallId,
      rating: rating,
      text: comment,
      type: "stall",
    };
    return axios.post(ADD_COMMENT_API_BASE_URL, req, {
      headers: headers,
    });
  }


  // Get a partial list of comments base on startIndex and endIndex
  async getCommentList(stallId, pageNum, pageSize) {
    return await axios.post(GET_COMMENT_API_BASE_URL + "/" + stallId, {
      orderBy: "timestamp",
      isLowToHigh: false,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  async sortCommentsByRating(stallId, isLowToHigh, pageNum, pageSize) {
    return await axios.post(GET_COMMENT_API_BASE_URL + "/" + stallId, {
      orderBy: "rating",
      isLowToHigh: isLowToHigh,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }
}

export default new StallRequest();
