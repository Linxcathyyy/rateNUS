<template>
  <div>
    <h1>Hostel List</h1>
    <div v-for="hostel in hostelList" :key="hostel.id" class="hostel">
      <div class="hostel-info">
        <h3>{{ hostel.id }}. {{ hostel.name }}</h3>
        <p>Location: {{ hostel.location }}</p>
        <p>Rating: {{ hostel.rating }}/5</p>
      </div>
      <p class="hostel-description">{{ hostel.description }}</p>
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
export default {
  name: "HostelList",
  data() {
    return {
      hostelList: [],
    };
  },
  methods: {
    getHostelList() {
      HostelRequest.getHostelList()
        .then((response) => {
          console.log(response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getHostelList();
  },
};
</script>

<style>
.hostel {
  list-style-type: none;
  background: rgba(201, 218, 241, 0.5);
  margin: 20px 50px;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.hostel-info {
  flex-shrink: 0;
  width: 20%;
  padding: 10px 40px;
}

.hostel-description {
  border-left: 3px solid darkgray;
  padding: 10px 30px;
  text-align: left;
  line-height: 200%;
}
</style>
