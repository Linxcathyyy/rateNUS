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
import TeamMemberDescription from "../components/util/TeamMemberDescriptions.js";
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
  methods: {
    getMemberDescriptionByName(name) {
      var memberDescription = TeamMemberDescription.getAllDescriptions()[name];
      if (memberDescription == undefined) {
        return "This member is lazy and did not provide any description.";
      }
      return memberDescription;
    },
  },
};
</script>

<style scoped></style>
