const app = Vue.createApp({
    data() {
        return {
            inicioSesion: false,
            registro: false,
            email:[],
            password:[],
            firstName:"",
            lastName:"",

        }
    },
    created() {
        
    },
    methods: {
        login(){
            axios.post('/api/login', "email=" + this.email + "&password=" + this.password)
                .then(() => {
                    window.location.href = "/accounts.html"
                })
                .catch(() => swal('Su usuario y contraseña son incorrectos'))
        },        
        inicioSesionA(){
            this.inicioSesion = true;
            this.registro = false;
        },
        registroA(){
            this.registro = true;
            this.inicioSesion = false;
        }
    },
   
    
})
let consol = app.mount("#app")