/* Get all buses for “Concessionaire" */
select * from public.bus 
where concessionaireId = (select id from public.concessionaire 
						  where name = 'Concessionaire 1');

/* Get all NVR devices for buses with type equal to “Bi-articulado” */
select device.* from public.device as device ,public.bus as bus
where bus.id = device.busId
and bus.type = 'Bi-articulado'
and device.devicetypeid = (select id from public.devicetype
						   where name = 'NVR');

/* Summarize the quantity of devices by status and bus motor  */
select device.status,bus.motor,count(device.id)
from public.device as device ,public.bus as bus
where bus.id = device.busId
group by device.status, bus.motor;