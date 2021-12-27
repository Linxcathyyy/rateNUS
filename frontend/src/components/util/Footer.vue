<template>
  <v-footer absolute dark class="grey lighten-3 px-4 py-12">
    <v-container>
      <v-row id="footer-link-row">
        <div class="link" @click="redirectToGithubRepo()">GitHub</div>
        <div class="link">About</div>
        <div class="link">Team</div>
        <div class="link" @click="redirectToGithubContributors()">
          Contributors
        </div>
        <div class="link">FAQ</div>
        <div class="link">Contribute to RateNUS</div>
      </v-row>
      <v-row>
        <div class="ml-2 my-4 data">
          Site
          <span class="link" @click="redirectToGithubLatestCommit()"
            >last updated</span
          >
          at {{ dateTime }}.
        </div>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
import GithubRequest from "../../httpRequests/GithubRequest.js";
export default {
  data() {
    return {
      dateTime: "",
    };
  },
  methods: {
    // add @click methods here
    redirectToGithubRepo() {
      window.open(GithubRequest.getRepoBaseUrl());
    },
    async getLatestCommitUrl() {
      return await GithubRequest.getLatestCommitUrl();
    },
    redirectToGithubLatestCommit() {
      this.getLatestCommitUrl().then((url) => {
        window.open(url);
      });
    },
    redirectToGithubContributors() {
      window.open(GithubRequest.getContributorsUrl());
    },
  },
  mounted() {
    // get website update time from git repo
    GithubRequest.getLatestCommitTimestamp().then((timestamp) => {
      console.log(timestamp);
      this.dateTime = new Date(timestamp);
    });
  },
};
</script>

<style>
#footer-link-row > div {
  margin: 0px 8px;
}

.data {
  color: #616161;
}

.link {
  font-weight: bold;
  color: #616161;
  background: transparent;
}

.link:hover {
  color: #ff6d00;
  cursor: pointer;
}
</style>
