const app = Vue.createApp({
    data(){
        return{
            email: "",
            contraseña: "",
            contraseña2: "",
            nombre: "",
            apellido: "",
            inicioSesion: false,
            registro: false,
            type: "password", hidden: true, show: false,
            type2: "password", hidden2: true, show2: false,
            data:{
                email: "",
                contraseña: "",
                contraseña2: ""
            }
        }
    },
    methods:{
    login() {
        axios.post('/api/login', "email=" + this.email + "&password=" + this.contraseña)
            .then(() => {
                window.location.href = "./paginas/home.html"
            })
            .catch(error => {
                swal({
                    text: 'Su usuario o contraseña es incorrecto, corrobore y vuelva a ingresar',
                    icon: "error"
                })
                .then(confirmation => {
                    this.contraseña = ""
                })
            })
    },
    registroCliente() {
        if(this.contraseña === this.contraseña2){
            axios.post('/api/clientes', "nombre=" + this.nombre + "&apellido=" + this.apellido + "&email=" + this.email + "&contraseña=" + this.contraseña, { headers: { 'content-type': 'application/x-www-form-urlencoded'} })
            .then(confirmation => {
                swal({
                title: 'Bienvenido a Ball D`or!!!',
                text: 'Hola ' + this.nombre + " " + this.apellido + ', su registro fue exitoso.',
                icon: "success"
                })
                .then(confirmation => {
                    axios.post('/api/login', "email=" + this.email + "&password=" + this.contraseña)
                    .then(confirmation => {
                        window.location.href = "./paginas/home.html"
                    })
                })
            })
            .catch(err => {
                swal({
                    text: err.response.data,
                    icon: "error"
                })
            })
        }else{
            swal({
                text: "Las contraseñas no coinciden.",
                icon: "error"
            })
        }
    },
    enviarSolicitud(){
        axios.post("/api/enviar-contraseña",`email=${this.data.email}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
        .then(response => {
            swal({
                title: "¡Genial!",
                text: "Solicitud de cambio de contraseña enviada a su correo. Por favor, revise su bandeja de entrada.",
                icon: "success"
            })
            .then(confirmation => {
                window.location.reload()
            })
        })
        .catch(error => {
            swal({
                text: error.response.data,
                icon: "error"
            })
        })
    },
    cambiarContraseña(){
        if(this.data.contraseña === this.data.contraseña2){
            axios.put(`/api/clientes/olvido-contraseña`,`email=${this.data.email}&contraseña=${this.data.contraseña}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    swal({
                    text: "Cambio de contraseña realizado exitosamente.",
                    icon: "success",
                    button: "Aceptar"
                })
                .then(response =>{
                    window.location.replace("index.html")
                })
            })
            .catch(error => {
                swal({
                    text: error.response.data,
                    icon: "error"
                })
            })
        }else{
            swal({
                text: "Las contraseñas no coinciden. Por favor, intente nuevamente.",
                icon: "error"
            })
        }
    },
    inicioSesionA(){
        this.inicioSesion = true;
        this.registro = false;
    },
    registroA(){
        this.inicioSesion = false;
        this.registro = true;
    },
    showPassword(){
        if(this.type === "password"){
            this.type = "text"
            this.hidden = false
            this.show = true
        }else{
            this.type = "password"
            this.hidden = true
            this.show = false
        }
    },
    showPassword2(){
        if(this.type2 === "password"){
            this.type2 = "text"
            this.hidden2 = false
            this.show2 = true
        }else{
            this.type2 = "password"
            this.hidden2 = true
            this.show2 = false
        }
    }
},
    
})
consol = app.mount("#app")
