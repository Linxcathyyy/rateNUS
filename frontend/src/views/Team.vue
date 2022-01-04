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
        :info="contributor"
        :description="getMemberDescriptionByName(contributor.login)"
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
      descriptions: {
        // add your descriptions here
        // Format:
        // {git id}: "{description}",
        JeffZincatz: "Test descrption",
      },
    };
  },
  async mounted() {
    this.contributors = await GithubRequest.getContributorsInfo();
  },
  methods: {
    getMemberDescriptionByName(name) {
      if (this.descriptions[name] == undefined) {
        return "This member is lazy and did not provide any description.";
      }
      return this.descriptions[name];
    },
  },
};
</script>

<style scoped></style>
