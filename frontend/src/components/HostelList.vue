<template>
    <div>
        <h1>Hostel List</h1>
        <div v-for="hostel in hostelList" :key="hostel.id">
            <h3>{{hostel.id}}. {{hostel.title}}</h3>
            <!-- <p>{{hostel.location}}</p> -->
        </div>
    </div>
</template>

<script>
import HostelRequest from '../httpRequests/HostelRequest';
export default {
    name:"HostelList",
    data() {
        return {
            hostelList: [],
        }
    },
    methods: {
        getHostelList() {
            HostelRequest.getHostelList().then((response) => {
                console.log(response.data);
                this.hostelList = response.data.slice(0, 2);
            }).catch(error => {
                console.log(error);
            })
        }
    },
    mounted() {

        this.getHostelList();
        // "https://jsonplaceholder.typicode.com/posts"
        // axios.get("http://127.0.0.1:8080").then((response) => {
        //     console.log(response);
        //     this.hostelList = response.data.slice(0, 2);
        // }).catch(error => {
        //     console.log(error);
        // })
    },
}
</script>

<style>

</style>