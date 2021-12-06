const app = Vue.createApp({
    data(){
        return{
            email: [],
            contraseña: [],
            nombre: "",
            apellido: "",
            inicioSesion: false,
            registro: false,
            
        }
    },
    methods:{
    login() {
        axios.post('/api/login', "email=" + this.email + "&password=" + this.contraseña)
            .then(() => {
                 window.location.href = "./paginas/home.html"
            })
            .catch(() => swal('Su usuario o contraseña es incorrecto, corrobore y vuelva a ingresar'))
    },
    registroCliente() {
        axios.post('/api/clientes', "nombre=" + this.nombre + "&apellido=" + this.apellido + "&email=" + this.email + "&contraseña=" + this.contraseña, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(() => swal('Hola ' + this.nombre + " " + this.apellido + ', Bienvenido a SuperLeague!!!'))
            .then(() => {                
                    window.location.href = "./paginas/home.html"

            })
            .catch(err => swal('Datos Incorrectos, corrobore los datos y vuelva a crear una cuenta ' + err))

    },
    inicioSesionA(){
        this.inicioSesion = true;
        this.registro = false;
    },
    registroA(){
        this.inicioSesion = false;
        this.registro = true;
    }
},
    
})
consol = app.mount("#app")
