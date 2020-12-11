<%-- 
    Document   : track
    Created on : 5 Sep, 2018, 11:57:07 PM
    Author     : Kanishka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>WIP: Order Tracking Concept</title>
  
  
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css'>

      <style>
      body {
  background:url("about1.png");
  font-family: "Roboto", sans-serif;
}

header {
  padding: 0.5em;
}

h5 {
  color: #343779;
  font-weight: 300;
}

h6,
.current {
  color: #33a9ac;
}

.complete {
  color: #343779;
}

.pending {
  color: #C9C9C9;
}

.border-left {
  border-style: solid;
  border-width: 0 0 0 0.2em;
  border-color: #343779;
}

.margin-top {
  margin-top: 3em;
}

.no-margin-bottom {
  margin-bottom: 0;
}

.wide {
  width: 95%;
}

.icon {
  opacity: 0.65;
}


</style>
</head>

<body>

  <div id="app"></div>

<!--truck icon from: https://www.flaticon.com/free-icon/delivery-truck_259551-->
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/react/0.13.0/react.min.js'></script>

  

    <script>
    	var Application = React.createClass({ displayName: "Application",
  getInitialState: function getInitialState() {
    return {
      orderDetails: {
        orderNumber: "#A61452B",
        status: "shipped",
        shippingLabel: {
          labelCreated: "true",
          labelCreatedDate: {
            month: "May",
            day: "23",
            year: "2017" } },


        startLocation: {
          address: {
            streetAddress: "123 Start St.",
            city: "Seattle",
            state: "WA" },

          date: {
            month: "May",
            day: "23",
            year: "2017" },

          status: "current",
          type: "start" },

        endLocation: {
          address: {
            streetAddress: "456 End St.",
            city: "New York City",
            state: "NY" },

          date: {
            month: "May",
            day: "28",
            year: "2017" },

          status: "future",
          type: "end" } } };



  },
  render: function render() {
    return (
      React.createElement("div", { className: "container" },
        React.createElement("div", { className: "row" },
          React.createElement("div", { className: "col s10 offset-s1 m6 offset-m3" },
            React.createElement("div", { className: "card hoverable" },
              React.createElement("header", { className: "card-title center-align" },
                React.createElement("h5", null, "Tracking Details"),
                React.createElement("h6", null, "Order ", this.state.orderDetails.orderNumber)),

              React.createElement(Image, { status: this.state.orderDetails.status }),
              React.createElement("div", { className: "card-content" },
                React.createElement(Details, {
                  status: this.state.orderDetails.status,
                  shippingLabel: this.state.orderDetails.shippingLabel,
                  startAddress: this.state.orderDetails.startLocation,
                  endAddress: this.state.orderDetails.endLocation })))))));







  } });


var Image = React.createClass({ displayName: "Image",
  render: function render() {
    var imgSrc, width, classes;
    if (this.props.status === "shipped") {
      width = "55%";
      imgSrc =
      "https://s3-us-west-2.amazonaws.com/s.cdpn.io/1206469/delivery-truck%20(1).svg";
      classes = "col offset-s3";
    } else if (this.props.status === "delivered") {
      imgSrc =
      "https://s3-us-west-2.amazonaws.com/s.cdpn.io/1206469/mailbox.svg";
      width = "45%";
      classes = "col offset-s4";
    } else {
      width = "45%";
      classes = "col offset-s4";
      imgSrc = "https://s3-us-west-2.amazonaws.com/s.cdpn.io/1206469/box.svg";
    }
    return (
      React.createElement("div", { className: "row no-margin-bottom" },
        React.createElement("div", { className: classes },
          React.createElement("img", { width: width, src: imgSrc, className: "icon" }))));



  } });


var Location = React.createClass({ displayName: "Location",
  render: function render() {
    var status, styling;
    if (this.props.type === "start") {
      if (this.props.status === "shipped") {
        status = "Shipped";
        styling = "row current margin-top";
      } else {
        status = "Shipped";
        styling = "row complete margin-top";
      }
    } else if (this.props.type === "end") {
      if (this.props.status === "shipped") {
        status = "Estimated";
        styling = "row pending no-margin-bottom margin-top";
      } else {
        status = "Delivered";
        styling = "row current no-margin-bottom margin-top";
      }
    }
    return (
      React.createElement("div", { className: styling },
        React.createElement("div", { className: "col s8" },
          React.createElement("div", null,
            this.props.address.streetAddress,
            React.createElement("br", null),
            this.props.address.city, ", ", this.props.address.state)),


        React.createElement("div", { className: "col s4 right-align" },
          React.createElement("date", null,
            status,
            React.createElement("br", null),
            this.props.date.month, " ", this.props.date.day))));




  } });


var NotShipped = React.createClass({ displayName: "NotShipped",
  render: function render() {
    return (
      React.createElement("div", { className: "center-align" }, "Not yet shipped. Check back later!"));

  } });


var Details = React.createClass({ displayName: "Details",
  render: function render() {
    var shippingDetails;
    if (this.props.status !== "pending") {
      shippingDetails =
      React.createElement(ShippingLabelCreated, {
        labelCreatedDate: this.props.shippingLabel.labelCreatedDate,
        startLocationDetails: this.props.startAddress,
        status: this.props.status,
        endLocationDetails: this.props.endAddress });


    } else {
      shippingDetails = React.createElement(NotShipped, null);
    }
    return React.createElement("div", { className: "container wide" }, shippingDetails);
  } });


var ShippingLabelCreated = React.createClass({ displayName: "ShippingLabelCreated",
  render: function render() {
    return (
      React.createElement("div", { className: "row border-left no-margin-bottom" },
        React.createElement("div", { className: "col s12" },
          React.createElement("div", null,
            React.createElement("div", { className: "row complete" },
              React.createElement("div", { className: "col s8" },
                React.createElement("div", null, "Label Created")),

              React.createElement("div", { className: "col s4 right-align" },
                React.createElement("date", null,
                  this.props.labelCreatedDate.month, " ",
                  this.props.labelCreatedDate.day))),



            React.createElement(Location, {
              address: this.props.startLocationDetails.address,
              date: this.props.startLocationDetails.date,
              status: this.props.status,
              type: this.props.startLocationDetails.type }),

            React.createElement(Location, {
              address: this.props.endLocationDetails.address,
              date: this.props.endLocationDetails.date,
              status: this.props.status,
              type: this.props.endLocationDetails.type })))));





  } });


React.render(React.createElement(Application, null), document.getElementById("app"));
    </script>




</body>

</html>
