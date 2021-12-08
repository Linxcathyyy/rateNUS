import axios from "axios";

const HOSTEL_API_BASE_URL = "http://localhost:8080/hostel";

class HostelRequest {
  // Get a complete list of hostels from the server
  getHostelList() {
    return axios.get(HOSTEL_API_BASE_URL);
  }

  // Get individual hostel data from server
  getIndividualHostel(hostelId) {
    return axios.get(HOSTEL_API_BASE_URL + "/" + hostelId);
  }

  // Post individual hostel comment to server
  postHostelComment(hostelId, comment, rating) {
    // below is temp implementation to see post content
    var req = {
      hostelId: hostelId,
      comment: comment,
      rating: rating,
    };
    return req;
    // todo: post hostel comment with hostelId to backend
  }
}

export default new HostelRequest();
