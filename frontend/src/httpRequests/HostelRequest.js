import axios from "axios";

const HOSTEL_API_BASE_URL = "https://www.ratenus.cyou:8080/hostel";
const COMMENT_API_BASE_URL = "https://www.ratenus.cyou:8080/comment";
const COMMENT_SORT_API_BASE_URL =
  "https://www.ratenus.cyou:8080/comment/hostel";

class HostelRequest {
  // Get a partial list of hostels based on startIndex and endIndex
  async getHostelList(pageNum, pageSize) {
    return await axios.post(HOSTEL_API_BASE_URL, {
      orderBy: "id",
      isLowToHigh: true,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  getSortedHostelList(isHighToLow) {
    return axios.get(HOSTEL_API_BASE_URL + "/isHighToLow/" + isHighToLow);
  }

  // Get individual hostel data from server
  getIndividualHostel(hostelId) {
    return axios.get(HOSTEL_API_BASE_URL + "/" + hostelId);
  }

  // Post individual hostel comment to server
  postHostelComment(hostelId, comment, rating, jwtToken, userId) {
    // below is temp implementation to see post content
    if (hostelId == undefined) {
      // check id is hostel id
      throw new Error(
        "Invalid id argument. Id must be a hostelId.\nId received: ",
        hostelId
      );
    }
    const headers = {
      Authorization: "Bearer " + jwtToken,
    };

    const req = {
      targetId: hostelId,
      rating: rating,
      text: comment,
      type: "hostel",
      userId: userId,
    };
    return axios.post(COMMENT_API_BASE_URL, req, {
      headers: headers,
    });
  }

  // Find hostels based on keywords
  findHostels(keyword, pageNum, pageSize) {
    return axios.post(HOSTEL_API_BASE_URL + "/search", {
      keyword: keyword,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  updateHostel(hostelId, jwtToken, username, data) {
    return axios.put(HOSTEL_API_BASE_URL + "/update/" + hostelId, data, {
      params: {
        token: jwtToken,
        username: username,
      },
    });
  }

  deleteHostel(hostelId, jwtToken, username) {
    return axios.delete(HOSTEL_API_BASE_URL + "/delete/" + hostelId, {
      params: {
        token: jwtToken,
        username: username,
      },
    });
  }

  addHostel(jwtToken, username, data) {
    return axios.post(HOSTEL_API_BASE_URL + "/new", data, {
      params: {
        token: jwtToken,
        username: username,
      },
    });
  }

  // Get a partial list of comments base on startIndex and endIndex
  async getCommentList(hostelId, pageNum, pageSize) {
    return await axios.post(COMMENT_SORT_API_BASE_URL + "/" + hostelId, {
      orderBy: "timestamp",
      isLowToHigh: false,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }

  async sortCommentsByRating(hostelId, isLowToHigh, pageNum, pageSize) {
    return await axios.post(COMMENT_SORT_API_BASE_URL + "/" + hostelId, {
      orderBy: "rating",
      isLowToHigh: isLowToHigh,
      pageNum: pageNum,
      pageSize: pageSize,
    });
  }
}

export default new HostelRequest();
