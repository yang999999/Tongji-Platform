var symptomName = last_month_day();

$(function(){

// SendData();
  init();
  init2();
    $("#el-dialog").addClass("hide");
  $(".close").click(function(event) {
    $("#el-dialog").addClass("hide");
  });

  var date = new Date();
     var numble = date.getDate();
     var today = getFormatMonth(new Date());
     $("#date1").html(today);
     $("#date2").html(today);
     $("#date3").html(today);
     $("#date4").html(today);


  lay('.demo-input').each(function(){
     laydate.render({
        type: 'month',
         elem: this,
         trigger: 'click',
         theme: '#95d7fb',
         calendar: true,
         showBottom: true,
         done: function () {
            console.log( $("#startDate").val())

         }
     })
 });

})



function init(){
  //地图
  var mapChart = echarts.init(document.getElementById('mapChart'));
  mapChart.setOption({
      color:["yellow"],
      bmap: {
          center: [120.43353,30.235966],
          zoom: 15,
          roam: true,

      },
      tooltip : {
          trigger: 'item',
          formatter:function(params, ticket, callback){
              console.log(params)
              return params.value[2] + ":<br>" + params.value[3]
          }
      },
      series: [{
          type: 'scatter',
          coordinateSystem: 'bmap',
          symbolSize: 20,
          data: [
            [120.440777,30.241729, '萧山国际机场'] ,
            [120.457532,30.240451, '北一区'] ,
            [120.452717,30.238391, '北二区'],
            [120.447759,30.23602, '北三区'],
            [120.436548,30.231028, '南区'],
           ]
      }]
  });
  mapChart.on('click', function (params) {
      $("#el-dialog").removeClass('hide');
      $("#reportTitle").html(params.value[2]);
      console.log(params);
  });

  var bmap = mapChart.getModel().getComponent('bmap').getBMap()
  bmap.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP ]}));
  bmap.setMapStyle({style:'midnight'})


  var pieChart1 = echarts.init(document.getElementById('pieChart1'));
  pieChart1.setOption({

    color:["#87cefa","#ff7f50","#32cd32","#da70d6",],

    legend: {
        y : '260',
        x : 'center',
        textStyle : {
            color : '#ffffff',

        },
         data : ['北一区','北二区','北三区','南区',],
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a}<br/>{b}<br/>{c}G ({d}%)"
    },
    calculable : false,
    series : [
        {
            name:'采集数据量',
            type:'pie',
            radius : ['40%', '70%'],
            center : ['50%', '45%'],
            avoidLabelOverlap: false,
            itemStyle : {
                normal : {
                    label : {
                        show : false,
                        position:"center",
                    },
                    labelLine : {
                        show : true
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        position : 'center',
                        textStyle : {
                            fontSize : '20',
                            fontWeight : 'bold'
                        }
                    }
                }
            },
            data:[
                {value:335, name:'北一区'},
                {value:310, name:'北二区'},
                {value:234, name:'北三区'},
                {value:135, name:'南区'}

            ]
        }
    ]
    });


    var lineChart = echarts.init(document.getElementById('lineChart'));
    lineChart.setOption({

      color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
      legend: {
          y : '260',
          x : 'center',
          textStyle : {
              color : '#ffffff',

          },
           data : ['北一区','北二区','北三区','南区',],
      },
      calculable : false,
      tooltip : {
          trigger: 'item',
          formatter: "{a}<br/>{b}<br/>{c}条"
      },
      yAxis: [
            {
                type: 'value',
                axisLine : {onZero: false},
                axisLine:{
                    lineStyle:{
                        color: '#034c6a'
                    },
                },

                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + "k条"
                    },
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        xAxis: [
            {
                type: 'category',
                data : ['8:00','10:00','12:00','14:00','16:00','18:00','20:00','22:00'],
                axisLine:{
                    lineStyle:{
                        color: '#034c6a'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                },
            }
        ],
        grid:{
                left: '5%',
                right: '5%',
                bottom: '20%',
                containLabel: true
        },
        series : [
          {
              name:'北一区',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[15, 0, 20, 45, 22.1, 25, 70, 55, 76]
          },
          {
              name:'北二区',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[25, 10, 30, 55, 32.1, 35, 80, 65, 76]
          },
          {
              name:'北三区',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[35, 20, 40, 65, 42.1, 45, 90, 75, 96]
          },
          {
              name:'南区',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[45, 30, 50, 75, 52.1, 55, 100, 85, 106]
          }
      ]
    });

    var histogramChart = echarts.init(document.getElementById('histogramChart'));
    histogramChart.setOption({

      color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
      legend: {
          y : '250',
          x : 'center',
          data:['北一区', '北二区','北三区','南区'],
          textStyle : {
              color : '#ffffff',

          }
      },

      calculable :false,


      grid:{
              left: '5%',
              right: '5%',
              bottom: '20%',
              containLabel: true
      },

      tooltip : {
          trigger: 'axis',
          axisPointer : {
              type : 'shadow'
          }
      },

      xAxis : [
          {
              type : 'value',
              axisLabel: {
                  show: true,
                  textStyle: {
                      color: '#fff'
                  }
              },
              splitLine:{
                  lineStyle:{
                      color:['#f2f2f2'],
                      width:0,
                      type:'solid'
                  }
              }

          }
      ],

      yAxis : [
          {
              type : 'category',
              data:['地表测斜(毫米)', '测斜数据采集数量(条)','噪音数量(条)'],
              axisLabel: {
                  show: true,
                  textStyle: {
                      color: '#fff'
                  }
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              }
          }
      ],

      series : [
          {
              name:'北一区',
              type:'bar',
              stack: '总量',
              itemStyle : { normal: {label : {show: true, position: 'inside'}}},
              data:[320, 302, 301]
          },
          {
              name:'北二区',
              type:'bar',
              stack: '总量',
              itemStyle : { normal: {label : {show: true, position: 'inside'}}},
              data:[120, 132, 101]
          },
          {
              name:'北三区',
              type:'bar',
              stack: '总量',
              itemStyle : { normal: {label : {show: true, position: 'inside'}}},
              data:[220, 182, 191]
          },
          {
              name:'南区',
              type:'bar',
              stack: '总量',
              itemStyle : { normal: {label : {show: true, position: 'inside'}}},
              data:[150, 212, 201]
          }

      ]
   });

   var lineChart2 = echarts.init(document.getElementById('lineChart2'));
   lineChart2.setOption({

     color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
     legend: {
         y : '260',
         x : 'center',
         textStyle : {
             color : '#ffffff',

         },
          data : ['北一区','北二区','北三区','南区',],
     },
     calculable : false,
     tooltip : {
         trigger: 'item',
         formatter: "{a}<br/>{b}<br/>{c}条"
     },
     yAxis: [
           {
               type: 'value',
               axisLine : {onZero: false},
               axisLine:{
                   lineStyle:{
                       color: '#034c6a'
                   },
               },

               axisLabel: {
                   textStyle: {
                       color: '#fff'
                   },
                   formatter: function (value) {
                       return value + "k条"
                   },
               },
               splitLine:{
                   lineStyle:{
                       width:0,
                       type:'solid'
                   }
               }
           }
       ],
       xAxis: [
           {
               type: 'category',
               data : ['8:00','10:00','12:00','14:00','16:00','18:00'],
               axisLine:{
                   lineStyle:{
                       color: '#034c6a'
                   },
               },
               splitLine: {
                   "show": false
               },
               axisLabel: {
                   textStyle: {
                       color: '#fff'
                   },
                   formatter: function (value) {
                       return value + ""
                   },
               },
               splitLine:{
                   lineStyle:{
                       width:0,
                       type:'solid'
                   }
               },
           }
       ],
       grid:{
               left: '5%',
               right: '5%',
               bottom: '20%',
               containLabel: true
       },
       series : [
         {
             name:'北一区',
             type:'line',
             smooth:true,
             itemStyle: {
                 normal: {
                     lineStyle: {
                         shadowColor : 'rgba(0,0,0,0.4)'
                     }
                 }
             },
             data:[15, 0, 20, 45, 22.1, 25,].reverse()
         },
         {
             name:'北二区',
             type:'line',
             smooth:true,
             itemStyle: {
                 normal: {
                     lineStyle: {
                         shadowColor : 'rgba(0,0,0,0.4)'
                     }
                 }
             },
             data:[25, 10, 30, 55, 32.1, 35, ].reverse()
         },
         {
             name:'北三区',
             type:'line',
             smooth:true,
             itemStyle: {
                 normal: {
                     lineStyle: {
                         shadowColor : 'rgba(0,0,0,0.4)'
                     }
                 }
             },
             data:[35, 20, 40, 65, 42.1, 45, ].reverse()
         },
         {
             name:'南区',
             type:'line',
             smooth:true,
             itemStyle: {
                 normal: {
                     lineStyle: {
                         shadowColor : 'rgba(0,0,0,0.4)'
                     }
                 }
             },
             data:[45, 30, 50, 75, 52.1, 55, 6].reverse()
         }
     ]
   });



}

function init2(){
  var lineChart3 = echarts.init(document.getElementById('lineChart3'));//点击点位后的监测值
  var lineChart3_Option = {

    color:["#87cefa","#ff7f50",],


    calculable : false,
    tooltip : {
        trigger: 'item',
        formatter: "{a}<br/>{b}<br/>{c}毫米"
    },
    dataZoom: {
         show: true,
         realtime : true,
         start: 0,
         end: 18,
         height: 20,
         backgroundColor: '#f8f8f8',
         dataBackgroundColor: '#e4e4e4',
         fillerColor: 'rgba(38,98,135,0.77)',
         handleColor: '#acddfd',
     },
    yAxis: [
          {
              type: 'value',
              interval: 2,
              axisLine : {onZero: false},
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },

              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + "毫米"
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              }
          }
      ],
      xAxis: [
          {
              type: 'category',
              data : symptomName,
              boundaryGap : false,
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },
              splitLine: {
                  "show": false
              },
              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + ""
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              },
          }
      ],
      grid:{
              left: '5%',
              right: '5%',
              bottom: '20%',
              containLabel: true
      },
      series : [
        {
            name:'沉降值',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[12, 11.81, 11.8, 11.8, 11.8, 11.7, 11.7,11.6, 11.6, 11.6, 11.5, 11.5, 11.45,11.43,11.42, 11.41, 11.4, 11.38, 11.37,11.36, 11.35, 11.34, 11.33, 11.32, 11.31,11.29, 11.27,11.27, 11.27,11.26, 11.25]
        },
          {
              name:'沉降值',
              type:'line',
              smooth:true,
              itemStyle: {
                  normal: {
                      lineStyle: {
                          shadowColor : 'rgba(0,0,0,0.4)'
                      }
                  }
              },
              data:[5, 4.8, 4.8, 4.8, 4.7, 4.7,4.6, 4.6, 4.6, 4.5, 4.5, 4.45,4.43,4.42, 4.41, 4.4, 4.38, 4.37,4.36, 4.35, 4.34, 4.33, 4.32, 4.31,4.29, 4.27,4.27, 4.27,4.26, 4.25]
          },
        {
            name:'沉降值',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[14, 13.8, 13.8, 13.8, 13.7, 13.7,13.6, 13.6, 13.6, 13.5, 13.5, 13.45,13.43,13.42, 13.41, 13.4, 13.38, 13.37,13.36, 13.35, 13.34, 13.33, 13.32, 13.31,13.29, 13.27,13.27, 13.27,13.26, 13.25]
        },
    ]
  };
   // $.ajax({
   //      url:'data/data/result',
   //      success:function (data){
   //          lineChart3_Option.xAxis.data = data.date_1;
   //          lineChart3_Option.series[0].data = data.value_1;
   //          lineChart3.setOption(lineChart3_Option);
   //      }
   //  });

  lineChart3.setOption(lineChart3_Option);
  var lineChart4 = echarts.init(document.getElementById('lineChart4'));//点击点位后的预测值
  var lineChart4_Option = {

    color:["#87cefa","#ff7f50",],
    calculable : false,
    tooltip : {
        trigger: 'item',
        formatter: "{a}<br/>{b}<br/>{c}毫米"
    },
    dataZoom: {
         show: true,
         realtime : true,
         start: 0,
         end: 18,
         height: 20,
         backgroundColor: '#f8f8f8',
         dataBackgroundColor: '#e4e4e4',
         fillerColor: 'rgba(38,98,135,0.77)',
         handleColor: '#87cefa',
     },
    yAxis: [
          {
              type: 'value',
              axisLine : {onZero: false},
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },

              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + "毫米"
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              }
          }
      ],
      xAxis: [
          {
              type: 'category',
              data : symptomName,
              boundaryGap : false,
              axisLine:{
                  lineStyle:{
                      color: '#034c6a'
                  },
              },
              splitLine: {
                  "show": false
              },
              axisLabel: {
                  textStyle: {
                      color: '#fff'
                  },
                  formatter: function (value) {
                      return value + ""
                  },
              },
              splitLine:{
                  lineStyle:{
                      width:0,
                      type:'solid'
                  }
              },
          }
      ],
      grid:{
              left: '5%',
              right: '5%',
              bottom: '20%',
              containLabel: true
      },
      series : [
        {
            name:'预测沉降值',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[5, 4.8, 4.8, 4.8, 4.7, 4.7,4.6, 4.6, 4.6, 4.5, 4.5, 4.45,4.43,4.42, 4.41, 4.4, 4.38, 4.37,4.36, 4.35, 4.34, 4.33, 4.32, 4.31,4.29, 4.27,4.27, 4.27,4.26, 4.25].reverse()
        },
    ]
  };

   // $.ajax({
   //      url:'data/data/result',
   //      success:function (data){
   //          lineChart4_Option.xAxis.data = data.date_2;
   //          lineChart4_Option.series[0].data = data.value_2;
   //          lineChart4.setOption(lineChart4_Option);
   //      }
   //  });
    lineChart4.setOption(lineChart4_Option);
  //点击点位后的监督学习组成
  var pieChart2 = echarts.init(document.getElementById('pieChart2'));
  pieChart2.setOption({
    color:["#32cd32","#ff7f50","#87cefa","#FD6C88","#4b5cc4","#faff72"],
    tooltip : {
     trigger: 'item',
     formatter: "{a}<br/>{b}<br/>{c}%"
    },
    calculable : true,
    series : [
        {
            name:'预测占比',
            type:'pie',
            radius : [30, 110],
            center : ['50%', '50%'],
            roseType : 'area',
            x: '50%',



            sort : 'ascending',
            data:[
                {value:5, name:'RNN'},
                {value:5, name:'CNN'},
                {value:5, name:'PINN'},
                {value:10, name:'随机森林'},
                {value:15, name:'Transformer'},
                {value:60, name:'CNN-LSTM'},
            ]
        }
    ]
  })

  //点击点位后的无监督学习算法组成
  var pieChart3 = echarts.init(document.getElementById('pieChart3'));
  pieChart3.setOption({
    color:["#32cd32","#ff7f50","#87cefa","#FD6C88","#4b5cc4","#faff72"],
    tooltip : {
     trigger: 'item',
     formatter: "{a}<br/>{b}<br/>{c}%"
    },
    calculable : true,
    series : [
        {
            name:'预测占比',
            type:'pie',
            radius : [30, 110],
            center : ['50%', '50%'],
            roseType : 'area',
            x: '50%',



            sort : 'ascending',
            data:[
                {value:30, name:'遗传算法'},
                {value:70, name:'粒子群算法'}
            ]
        }
    ]
  })
}


var chartMapElementId = 'mapChart';
// var chartMapElement = document.getElementById(chartMapElementId); 这种有问题，不显示图
//相对路径，页面的当前目录
// var jsonFileName = 'json/map.json';

//绝对路径，根目录
var jsonFileName = 'map.json';
var jsonFileNamechart_1 = 'chart_1.json';
var jsonFileNamechart_2 = 'chart_2.json';

function get_legend(data) {
    var listLegend = new Array();

    for (i=0; i<data.length; i++) {
        n = data[i]["name"];
        listLegend.push(n);
    }
    console.log("get_legend", listLegend);
    return listLegend
}

function get_value(data) {
    var listLegend = new Array();
    var listValue = new Array();
    for (i=0; i<data.length; i++) {
        n = data[i][2];
        v = data[i][3];
        tmp = {"name": n, "value": v};
        listLegend.push(n);
        listValue.push(tmp);
    }
    console.log("get_value", listLegend, listValue);
    return [listLegend, listValue]
}

function get_value2(data) {
    var listLegend = new Array();
    var listValue = new Array();
    for (i=0; i<data.length; i++) {
        n = data[i][2];
        v = [data[i][3], data[i][4], data[i][5]];
        tmp = {"name": n, "data": v};
        listLegend.push(n);
        listValue.push(tmp);
    }
    console.log("get_value2", listLegend, listValue);
    return [listLegend, listValue]
}


function get_value3(data) {
    var listLegend = new Array();
    var listValue = new Array();
    for (i=0; i<data.length; i++) {
        n = data[i][2];
        v = [data[i][3], data[i][4], data[i][5], data[i][3], data[i][4], data[i][5], data[i][3], data[i][4], data[i][5]];
        tmp = {"name": n, "data": v};
        listLegend.push(n);
        listValue.push(tmp);
    }
    console.log("get_value3", listLegend, listValue);
    return [listLegend, listValue]
}


function async_map_data() {

    // 异步加载数据
    $.getJSON(jsonFileName).done(function (data) {
        // 1
        var chartMapElement = document.getElementById(chartMapElementId);
        console.log(chartMapElementId, "async_data")
        var myChart = echarts.init(chartMapElement);
        console.log(data)
        myChart.setOption({
            series: [{
                data: data
            }]
        });

        // 2
        var pieChart1Element = document.getElementById("pieChart1");
        var pieChart1 = echarts.init(pieChart1Element);
        rs = get_value(data);
        console.log(rs);
        pieChart1.setOption({
            legend: {
                data: rs[0]
            },
            series: [{
                data: rs[1]
            }]
        })

        //3
        var histogramChartElement = document.getElementById('histogramChart');
        var histogramChart = echarts.init(histogramChartElement);
        rs = get_value2(data);
        console.log(rs);
        histogramChart.setOption({
            legend: {
                data: rs[0]
            },
            series: rs[1]
        })
        // 4
        var lineChart = echarts.init(document.getElementById('lineChart'));
        rs = get_value3(data);
        lineChart.setOption({
            legend: {
                data: rs[0]
            },
            series: rs[1]
        })

        // end
    });//end $.getJSON

    // 异步加载数据
    $.getJSON(jsonFileNamechart_1).done(function (data) {
        console.log(data);

        var lineChart2 = echarts.init(document.getElementById('lineChart2'));
        lineChart2.setOption({
            legend: {
                data: get_legend(data)
            },
            series: data
        })
    });//end $.getJSON

    $.getJSON(jsonFileNamechart_2).done(function (data) {
        t0 = document.getElementById('t0');
        t0.innerText = data[0];

        t1 = document.getElementById('t1');
        t1.innerText = data[1];

        t2 = document.getElementById('t2');
        t2.innerText = data[2];

        t3 = document.getElementById('t3');
        t3.innerText = data[3];

        t4 = document.getElementById('t4');
        t4.innerText = data[4];

        t5 = document.getElementById('t5');
        t5.innerText = data[5];
    });//end $.getJSON

}

// function SendData(){
//     $("#btn1").click(function (){
//         $.ajax({
//             url:'data/date',  //后端需要设置对应路由
//             type:'post',
//             data:{
//                 startdate: $("#datepicker").val()
//                 enddate: $("datepicker1").val()
//             },
//             dataType:'JSON',
//             success:function (res){
//                 console.log(res);
//             }
//         })
//         }
//
//     )
// }


