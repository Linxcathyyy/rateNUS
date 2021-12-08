import axios from "axios";

const HOSTEL_API_BASE_URL = "http://localhost:8080/hostel";
const COMMENT_API_BASE_URL = "http://localhost:8080/comment";

class HostelRequest {
  // Get a complete list of hostels from the server
  getHostelList() {
    return axios.get(HOSTEL_API_BASE_URL);
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
      return axios.post(HOSTEL_API_BASE_URL, {
          keyword: keyword
      });
  }
}

export default new HostelRequest();
