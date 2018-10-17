<template>
    <div>
        <div id="main-content" class="container">
            <div class="row">
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="connect">WebSocket connection:</label>
                            <button id="connect" class="btn btn-default" type="submit" :disabled="connected == true" @click.prevent="connect">Connect</button>
                            <button id="disconnect" class="btn btn-default" type="submit" :disabled="connected == false" @click.prevent="disconnect">Disconnect
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="name">What is your name?</label>
                            <input type="text" id="name" class="form-control" v-model="send_message" placeholder="Your name here...">
                        </div>
                        <button id="send" class="btn btn-default" type="submit" @click.prevent="send">Send</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table id="conversation" class="table table-striped">
                        <thead>
                            <tr>
                                <th>Greetings</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, i) in received_messages" :key="i">
                                <td>{{ item.id }}</td> <td>{{ item.contents }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import axios from 'axios'

export default {
  name: 'HelloWorld',
  data () {
    return {
      page_index: 0,
      received_messages: [],
      send_message: null,
      connected: false
    }
  },  
  methods: {
    send() {
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { name: this.send_message };
        //this.stompClient.send("/app/hello", JSON.stringify(msg), {});
      }
    },
    connect() {
      var _self = this;
      this.socket = new SockJS("http://localhost:8080/ws");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          //this.stompClient.subscribe("/topic/greetings", tick => {
          this.stompClient.subscribe("/subscribe/broadcast", tick => {
            
            // event 발생될 경우 들어옴.
            var event_message = JSON.parse(tick.body).contents;
            //this.received_messages.push(JSON.parse(tick.body).contents);        
            
            // 변경사항 없음
            if(event_message === undefined ) {
            // 화면 처음 호출
            console.log("_self.page_index :"+_self.page_index);
                if(_self.page_index == 0 ) {
                    axios.get('http://localhost:8080/v1/lists', { headers: { 'Access-Control-Allow-Origin': true }})
                    .then(function (response) {
                        _self.received_messages.push(response.data);
                    })
                    .catch(function (error) {
                    console.log('an error occured.'+error);
                    })
                _self.page_index++;
                } 
            } else if (event_message === "flag") {
               // call api
               axios.get('http://localhost:8080/v1/lists', { headers: { 'Access-Control-Allow-Origin': true }})
                  .then(function (response) {
                      _self.received_messages.push(response.data);
                  })
                  .catch(function (error) {
                    console.log('an error occured.'+error);
                  })
          }
         })
        
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      )
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    }
  },
  mounted() {
    // this.connect();
  }
}
</script>

<style scoped>

</style>
