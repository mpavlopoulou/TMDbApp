package gr.mpav.tmdbapp.utils.adapters

import gr.mpav.tmdbapp.utils.database.DBShow
import java.util.*

// Data classes to hold show details for every section
open class DetailAdapterItem(val id:String = UUID.randomUUID().toString())

class HeaderItem (val backDropPath:String, val posterPath:String, val title:String, val releaseDate:String, val rating:Float, val genre:String ): DetailAdapterItem(UUID.randomUUID().toString())
class WatchListItem(val mShow:DBShow): DetailAdapterItem(UUID.randomUUID().toString())
class DescriptionItem (val overview: String ): DetailAdapterItem(UUID.randomUUID().toString())
class TrailerItem (val key: String ): DetailAdapterItem(UUID.randomUUID().toString())