import axios from "axios";

const HOSTEL_API_BASE_URL = "http://localhost:8080/hostel";
const COMMENT_API_BASE_URL = "http://localhost:8080/comment";
const COMMENT_SORT_API_BASE_URL = "http://localhost:8080/comment/hostel";

class HostelRequest {
  // Get a partial list of hostels base on startIndex and endIndex
  async getHostelList(pageNum, pageSize) {
    return await axios.post(HOSTEL_API_BASE_URL, {
      pageNum: pageNum,
      pageSize: pageSize
    });
  }

  getSortedHostelList(isHighToLow) {
    return axios.get(HOSTEL_API_BASE_URL + "/isHighToLow/" + isHighToLow);
  }

  // Get individual hostel data from server
  getIndividualHostel(hostelId) {
    return axios.get(HOSTEL_API_BASE_URL + "/" + hostelId);
  }

  // Get a complete list of comments for a specific hostel from server
  getCommentList(hostelId) {
    return axios.get(COMMENT_API_BASE_URL + "/hostel/" + hostelId);
  }

  // Post individual hostel comment to server
  postHostelComment(hostelId, comment, rating) {
    // below is temp implementation to see post content
    if (hostelId == undefined) {
      // check id is hostel id
      throw new Error(
        "Invalid id argument. Id must be a hostelId.\nId received: ",
        hostelId
      );
    }
    const req = {
      targetId: hostelId,
      rating: rating,
      text: comment,
      type: "hostel"
    };
    return axios.post(COMMENT_API_BASE_URL, req);
  }

  // Find hostels based on keywords
  findHostels(keyword) {
      return axios.post(HOSTEL_API_BASE_URL + "/search", {
          keyword: keyword
      });
  }

  // sortHostelsFromLowestToHighestRating(keyword) {

  // }

  // sortHostelsFromHighestToLowestRating(keyword) {

  // }

  // resetHostelsSorting(keyword) {

  // }

  sortCommentsFromLowestToHighestRating(hostelId) {
    return axios.get(COMMENT_SORT_API_BASE_URL + "/" + hostelId + "/" + false);
  }

  sortCommentsFromHighestToLowestRating(hostelId) {
    return axios.get(COMMENT_SORT_API_BASE_URL + "/" + hostelId + "/" + true);
  }

  resetCommentsSorting(hostelId) {
    return axios.get(COMMENT_SORT_API_BASE_URL + "/" + hostelId);
  }
}

export default new HostelRequest();
