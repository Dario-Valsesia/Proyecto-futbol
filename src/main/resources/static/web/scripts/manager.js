const app = Vue.createApp({
    data() {
        return {
            email: "",
            contraseña: ""
        }
    },
    created(){

    },
    methods: {
        verificarAdmin(){
            axios.post('/api/login',`email=${this.email}&password=${this.contraseña}`,
            {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.href = "./paginas/panel-admin.html")
            .catch(() => swal('Usuario y/o contraseña incorrectos'))
        }
    }
})
app.mount("#app")