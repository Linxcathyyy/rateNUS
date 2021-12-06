<template>
    <div>
        <h1>Hostel List</h1>
        <div v-for="hostel in hostelList" :key="hostel.id">
            <h3>{{hostel.id}}. {{hostel.title}}</h3>
            <p>{{hostel.body}}</p>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name:"HostelList",
    data() {
        return {
            hostelList: [],
        }
    },
    mounted() {
        axios.get("https://jsonplaceholder.typicode.com/posts").then((response) => {
            console.log(response.data);
            this.hostelList = response.data.slice(0, 2);
        }).catch(error => {
            console.log(error);
        })
    },
}
</script>

<style>

</style>