<template>
  <div>
    <h1 class="my-4 text-left">Team</h1>
    <v-divider class="mb-8"></v-divider>
    <v-container
      id="team-cards"
      v-for="contributor in this.contributors"
      :key="contributor.id"
    >
      <TeamCard
        :name="contributor.login"
        :avatarUrl="contributor.avatar_url"
        :url="contributor.html_url"
        description="Test Placeholder Description"
      />
    </v-container>
  </div>
</template>

<script>
import TeamCard from "../components/commons/TeamCard.vue";
import GithubRequest from "../httpRequests/GithubRequest.js";
export default {
  components: {
    TeamCard,
  },
  data() {
    return {
      contributors: [],
    };
  },
  async mounted() {
    this.contributors = await GithubRequest.getContributorsInfo();
  },
};
</script>

<style scoped></style>
