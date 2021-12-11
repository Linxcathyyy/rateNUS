<template>
  <div>
    <h1>Hostel List</h1>
    <SearchBar @handle-search="handleSearch" searchHint="Search for hostels" />
    <div v-for="hostel in hostelList" :key="hostel.id">
      <div @click="goToViewMorePage(hostel.id)" id="hostel-click">
        <Hostel :hostel="hostel" />
      </div>
    </div>
    <div class="text-center">
      <v-pagination
        v-model="currentPage"
        :length="4"
        circle
      ></v-pagination>
    </div>
    <!-- <Pagination :items="hostelList" :currentPage="currentPage" :pageSize="pageSize" v-on:page:update="updatePage"/> -->
  </div>
</template>


<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Hostel from "../components/hostels/Hostel.vue";
import SearchBar from "../components/util/SearchBar";
// import Pagination from "../components/util/Pagination.vue";

export default {
  name: "Hostels",
  components: {
    Hostel,
    SearchBar,
    // Pagination
  },

  data() {
    return {
      hostelList: [],
      currentPage: 1,
      pageSize: 1,
    };
  },

  methods: {
    async getHostelList(pageNum) {
      HostelRequest.getHostelList(pageNum)
        .then((response) => {
          this.hostelList = response.data;
          console.log(this.hostelList);
          // if (this.hostelList.length == 0 && this.currentPage > 1) {
          //   this.updatePage(this.currentPage - 1);
          // }
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },

    goToViewMorePage(hostelId) {
      this.$router.push("/hostels/" + hostelId);
    },

    handleSearch(keyword) {
      HostelRequest.findHostels(keyword)
        .then((response) => {
          console.log("search result" + response.data);
          this.hostelList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
 
    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      await this.getHostelList(pageNumber);
    },
  },

  async mounted() { 
    await this.getHostelList(this.currentPage);
  },

  emits: ["handle-search"],
};
</script>

<style scope>
#hostel-click:hover {
  cursor: pointer;
}

.search-bar {
  float: right;
}
</style>
