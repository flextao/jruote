require 'activerecord'
require 'java'

$active_record_config = {
  :adapter => "jdbc",
  :driver => "org.postgresql.Driver",
  :url => "jdbc:postgresql://localhost:5432/#{java.lang.System.getProperty("database_name")}",
  :username => "postgres",
  :password => "sa",
  :pool => 3
}

def establish_active_record_connection
  ActiveRecord::Base.establish_connection($active_record_config)
  ActiveRecord::Base.logger.info("active record connection configuration: #{$active_record_config.inspect}")
end
