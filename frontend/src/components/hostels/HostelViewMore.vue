<template>
  <div v-if="hostel != null">
    <h1>Hostel View More with Id {{ $route.params.hostelId }}</h1>
    <p>Name: {{ hostel.name }}</p>
    <p>Rating: {{ hostel.rating }}/5</p>
    <p>Location: {{ hostel.location }}</p>
    <p>Description: {{ hostel.description }}</p>
    <Hostel :hostel="this.hostel" />
    <CommentForm />
  </div>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import Hostel from "./Hostel.vue";
import CommentForm from "../CommentForm.vue";

export default {
  name: "HostelViewMore",
  components: { Hostel, CommentForm },
  data() {
    return {
      hostel: null,
    };
  },
  methods: {
    getIndividualHostel() {
      HostelRequest.getIndividualHostel(this.$route.params.hostelId)
        .then((response) => {
          this.hostel = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getIndividualHostel();
  },
};
</script>

<style></style>
