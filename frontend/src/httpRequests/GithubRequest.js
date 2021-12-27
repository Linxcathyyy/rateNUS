import axios from "axios";

const GITHUB_REPO_BASE_URL = "https://github.com/Linxcathyyy/rateNUS";
const GITHUB_API_URL =
  "https://api.github.com/repos/Linxcathyyy/rateNUS/commits";
const GITHUB_CONTRIBUTORS_URL =
  "https://github.com/Linxcathyyy/rateNUS/graphs/contributors";

class GithubRequest {
  getRepoBaseUrl() {
    return GITHUB_REPO_BASE_URL;
  }

  async getAllCommitInfo() {
    return (await axios.get(GITHUB_API_URL)).data;
  }

  async getLatestCommitInfo() {
    return (await this.getAllCommitInfo())[0];
  }

  async getLatestCommitUrl() {
    return (
      GITHUB_REPO_BASE_URL + "/commit/" + (await this.getLatestCommitInfo()).sha
    );
  }

  async getLatestCommitTimestamp() {
    return (await this.getLatestCommitInfo()).commit.author.date;
  }

  async getNumberOfCommits() {
    return (await this.getAllCommitInfo()).length;
  }

  getContributorsUrl() {
    return GITHUB_CONTRIBUTORS_URL;
  }
}

export default new GithubRequest();
