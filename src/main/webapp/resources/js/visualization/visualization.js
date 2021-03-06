var gauges = [];

function createGauge(name, label, min, max)
{
	var config = 
	{
		size: 340,
		label: label,
		min: undefined != min ? min : 0,
		max: undefined != max ? max : 100,
		minorTicks: 5
	}
	
	var range = config.max - config.min;
	config.greenZones = [{ from: config.min + range*0, to: config.min + range*0.75 }];
	config.yellowZones = [{ from: config.min + range*0.75, to: config.min + range*0.9 }];
	config.redZones = [{ from: config.min + range*0.9, to: config.max }];
	
	gauges[name] = new Gauge(name + "GaugeContainer", config);
	gauges[name].render();
}
function updateGauges(key,value)
{
	try{
		gauges[key].redraw(value);
	}catch(err){
		console.log(err.message);
	}
	
}
