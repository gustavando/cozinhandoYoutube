package cozinhandoYoutube

object Links {
	def canalCountVideos(user:String):String={
	  "http://gdata.youtube.com/feeds/api/users/"+user+"/uploads?max-results=1&start-index="+"1"+"&alt=rss&lr=pt"  
	}
	def canalQuery(user:String):String={
	  "http://gdata.youtube.com/feeds/api/users/"+user+"/uploads?max-results=50&start-index="+"1"+"&alt=rss&lr=pt"  
	}
	def canalQuery(user:String, start:Int):String={
	  "http://gdata.youtube.com/feeds/api/users/"+user+"/uploads?max-results=50&start-index="+start+"&alt=rss&lr=pt"
	}
	def forVideo(tag:String):String={
	  "http://gdata.youtube.com/feeds/api/videos/"+tag
	}
	
	
}